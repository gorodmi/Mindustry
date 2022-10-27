package mindustry.ui.layout;

public interface TreeLayout{
    void layout(TreeNode root);

    class TreeNode<T extends TreeNode>{
        public float width, height, x, y;

        //should be initialized by user
        public T[] children;
        public T parent;

        //internal stuff
        public float mode, prelim, change, shift, cachedWidth = -1f;
        public int number = -1, leaves, cachedDepth = -1;
        public TreeNode thread, ancestor;

        public boolean isLeaf(){
            return children == null || children.length == 0;
        }

        public float calcWidth(int depth){
            if(children == null) return width;
            float cWidth = 0;
            for(T node : children){
                cWidth += depth > 0 ? node.calcWidth(depth - 1) : width;
            }
            return Math.max(width, cWidth);
        }

        public int depth() {
            if(children == null) return 0;
            if(cachedDepth > 0) return cachedDepth;
            int depth = 0;
            for(T node : children) {
                depth = Math.max(depth, node.depth() + 1);
            }
            return cachedDepth = depth;
        }
    }
}
