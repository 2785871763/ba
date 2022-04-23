package datastructure;

import java.util.Scanner;

/**
 * @author zje
 * @createDate 2021-12-16
 * @desc 红黑树
 */
public class RBTree<K extends Comparable<K>, V> {

    public static void main(String[] args) {
        concurrenthash
    }

    /**
     * 树根和节点
     */
    private RBNode root;

    /**
     * 插入方法
     */
    public void insert(K k,V v){
        RBNode node = new RBNode();
        node.key = k;
        node.value = v;
        // 新节点一定是红色
        node.red = true;
        insert(node);
    }

    /**
     * 左旋方法示意图，对x节点进行左旋
     *        px                 px
     *        |                  |
     *        x                 rx
     *      /  \               /  \
     *     lx   rx    -->     x   rrx
     *         /  \          / \
     *       rlx  rrx      lx  rlx
     * 1. 旋转节点x的右节点（rx）上升成为x节点的父节点,
     * 2. 旋转节点x的右节点的左节点（rlx）成为旋转节点的右节点
     * 左旋方法代码流程
     * 1.（如果存在px）将rx的父节点设置为px，将px的右/左子节点设为rx,（如果不存在px说明是root）将root节点设置                       为rx
     * 2. 将x的父节点设为rx，将rx的左子节点设为x
     * 3. 将rlx的父节点设为x，将x的右子节点设为rlx
     */
    private void leftRotate(RBNode x) {
        RBNode rx = x.right;
        RBNode px = x.parent;
        RBNode rlx = rx.left;
        // 1.（如果存在px）将rx的父节点设置为px，将px的右/左子节点设为rx，（如果不存在px说明是root）将root节点                      设置为rx
        if(px != null) {
            rx.parent = px;
            if(px.left == x) {
                px.left = rx;
            }else {
                px.right = rx;
            }
        }else {
            root = rx;
        }
        // 2. 将x的父节点设为rx，将rx的左子节点设为x
        x.parent = rx;
        rx.left = x;
        // 3. 将rlx的父节点为x，将x的右子节点设为rlx
        x.right = rlx;
        if(rlx != null) {
            rlx.parent = x;
        }
    }

    /**
     * 右旋方法示意图，对x节点进行右旋
     *        px                 px
     *        |                  |
     *        x                 lx
     *      /  \               /  \
     *     lx  rx    -->     llx   x
     *    /  \                    / \
     *  llx  lrx                lrx  rx
     * 1. 旋转节点x的左节点（lx）上升成为x节点的父节点,
     * 2. 旋转节点x的左节点的右节点（rlx）成为旋转节点的左节点
     * 左旋方法代码流程
     * 1.（如果存在px）将lx的父节点设置为px，将px的右/左子节点设为lx,（如果不存在px说明是root）将root节点设置                       为lx
     * 2. 将x的父节点设为lx，将lx的右子节点设为x
     * 3. 将lrx的父节点设为x，将x的左子节点设为lrx
     */
    private void rightRotate(RBNode x){
        RBNode px = x.parent;
        RBNode lx = x.left;
        RBNode lrx = lx.right;
        // 1.（如果存在px）将lx的父节点设置为px，将px的右/左子节点设为lx,（如果不存在px说明是root）将root节点                       设置为lx
        if(px != null){
            lx.parent = px;
            if(px.left == x){
                px.left = lx;
            }else{
                px.right = lx;
            }
        }else{
            root = lx;
        }
        // 2. 将x的父节点设为lx，将lx的右子节点设为x
        x.parent = lx;
        lx.right = x;
        // 3. 将lrx的父节点设为x，将x的左子节点设为lrx
        x.left = lrx;
        if(lrx != null){
            lrx.parent = x;
        }
    }

    public static void main(String[] args) {
        Integer a = 22;
        Integer b = 25;
        System.out.println(a.compareTo(b));
    }

    /**
     * 插入方法
     */
    private void insert(RBNode node){
        // 如果根节点为空，设置根节点并修改颜色
        if(root == null){
            root = node;
            root.red = false;
            return;
        }
        // 从根节点开始查找父节点
        RBNode p = root;
        while (p != null){
            node.parent = p;
            // 当前节点与新节点比较大小，> 0 从右侧查找，< 0 从左侧查找，= 0 value覆盖
            int num = node.key.compareTo(p.key);
            if(num == 0){
                p.value = node.value;
                return;
            }
            p = num > 0 ? p.right : p.left;
        }
        p = node.parent;
        // 判断新节点插入父节点的左还是右
        if(node.key.compareTo(p.key) > 0 ){
            p.right = node;
        }else{
            p.left = node;
        }
        // 如果父节点是红色，形成双红，需要进行结构修复
        if(p.red){
            insertRepair(node);
        }
    }

    /**
     * 插入修复: pp：爷爷节点  p：父节点 u：叔叔节点，隔壁王叔叔
     * 如果当前节点的父节点是黑色，而当前节点是红色，不会破坏红黑树性值
     * 但是当前节点的父节点是红色就形成了双红情况，需要进行修复处理
     * 场景1. 如果父节点和叔叔节点也是红色，
     *       将爷爷节点变为红色，父节点和叔叔节点变为黑色，再以爷爷节点作为当前节点进行后续处理
     *         pp                （红）pp
     *       /   \                /    \
     *  （红）p  （红）u    -->    p      u
     *         |                    |
     *     （红）n                （红）n
     *
     * 场景2. 如果叔叔节点为空或黑色，且父节点为爷爷节点的左节点，且当前节点为父节点的左节点
     *       爷爷节点变红，父节点变黑，右旋爷爷节点
     *        pp             （红）pp                 p
     *      /   \             /   \                /  \
     *  （红）p   u   -->     p     u    -->  （红）n  （红）pp
     *    /                 /                           \
     *（红）n             （红）n                          u
     *
     * 场景3. 如果叔叔节点为空或黑色，且父节点为爷爷节点的左节点，且当前节点为父节点的右节点
     *       左旋父节点后就会变为场景2了，然后指定父节点为当前节点继续场景2操作
     *      pp                 pp
     *     /  \               /  \
     * （红）p  u   -->  （红）n    u
     *      \               /
     *  （红）n          （红）p
     *
     * 场景4. 如果叔叔节点为空或黑色，且父节点为爷爷节点的右节点，且当前节点为父节点的右节点
     *       爷爷节点变红，父节点变黑，左旋爷爷节点
     *     pp             （红）pp                 p
     *    /  \              /  \                /   \
     *   u （红）p   -->    u    p    -->  （红）pp（红）n
     *         \                \             /
     *     （红）n           （红）n           u
     *
     * 场景5. 如果叔叔节点为空或黑色，且父节点为爷爷节点的右节点，且当前节点为夫节点的左节点
     *       右旋父节点后就会变为场景4了，然后指定父节点为当前节点继续场景4操作
     *      pp                 pp
     *     /  \               /  \
     *    u（红）p   -->      u（红）n
     *       /                    \
     *  （红）n                 （红）p
     *
     */
    private void insertRepair(RBNode n){
        RBNode p = n.parent;
        RBNode pp = p.parent;
        RBNode u;
        // 如果当前节点的父节点是爷爷节点的左节点
        if(pp.left == p){
            u = pp.right;
            // 场景1: 如果叔叔节点也是红色
            if(u != null && u.red){
                // 将爷爷节点变为红色,父节点和叔叔节点变为黑色，再以爷爷节点作为当前节点进行后续处理
                p.red = false;
                u.red = false;
                pp.red = true;
                if(pp.parent == null){
                    pp.red = false;
                }else if (pp.parent.red) {
                    insertRepair(p);
                }
            }else{
                // 场景2. 如果叔叔节点为空或黑色，且父节点为爷爷节点的左节点，且插入节点为父节点的左节点
                if(n == p.left){
                    // 爷爷节点变红，父节点变黑，右旋爷爷节点
                    pp.red = true;
                    p.red = false;
                    rightRotate(pp);
                }else{
                    // 场景3. 如果叔叔节点为空或黑色，且父节点为爷爷节点的左节点，且当前节点为父节点的右节点
                    // 左旋父节点后就会变为场景2了，继续场景2操作
                    leftRotate(p);
                    insertRepair(p);
                }
            }
        }else{
            u = pp.left;
            // 场景1: 如果叔叔节点也是红色
            if(u != null && u.red){
                // 将爷爷节点变为红色,父节点和叔叔节点变为黑色，再以爷爷节点作为当前节点进行后续处理
                p.red = false;
                u.red = false;
                pp.red = true;
                if(pp.parent == null){
                    pp.red = false;
                }else if (pp.parent.red) {
                    insertRepair(p);
                }
            }else{
                // 场景3. 如果叔叔节点为空或黑色，且父节点为爷爷节点的右节点，且插入节点为父节点的右节点
                if(n == p.right){
                    // 爷爷节点变红，父节点变黑，左旋爷爷节点
                    pp.red = true;
                    p.red = false;
                    leftRotate(pp);
                }else{
                    // 场景4. 如果叔叔节点为空或黑色，且父节点为爷爷节点的右节点，且当前节点为父节点的左节点
                    // 左旋父节点后就会变为场景2了，继续场景2操作
                    rightRotate(p);
                    insertRepair(p);
                }
            }
        }
    }

    /**
     * 红黑树的节点
     */
    static class RBNode<K extends Comparable<K>, V> {
        /**
         * 父节点
         */
        private RBNode parent;
        /**
         * 左节点
         */
        private RBNode left;
        /**
         * 右节点
         */
        private RBNode right;
        /**
         * 节点颜色
         */
        private boolean red;
        private K key;
        private V value;
    }


}