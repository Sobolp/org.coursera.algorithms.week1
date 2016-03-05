package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by SoBoLp on 3/4/16.
 */
public class KdTree {
    private Node root;
    private int count;

    /**
     * construct an empty set of points
     */
    public KdTree() {
        count = 0;
    }

    /**
     * is the set empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * number of points in the set
     *
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * add the point to the set (if it is not already in the set)
     *
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException("argument is null");
        if (root == null) {
            root = new Node(p);
            root.setRect(new RectHV(0.0, 0.0, 1.0, 1.0));
            count++;
        } else {
            if (!this.contains(p)) {
                Node next = root;
                Node last = null;
                while (next != null) {
                    last = next;
                    next = getNext(last, p);
                }
                new Node(p, last);
                count++;
            }
        }
    }

    /**
     * does the set contain point p?
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        Node next = root;
        Node last;
        while (next != null) {
            if (next.getP().equals(p))
                return true;
            last = next;
            next = getNext(last, p);
        }
        return false;
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        for (Node n : getAllSubTree(root)) {
            Point2D p = n.getP();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            StdDraw.point(p.x(), p.y());
            if (n.getLevel() % 2 == 0) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(.001);
                StdDraw.line(p.x(), n.getRect().ymin(), p.x(), n.getRect().ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(.001);
                StdDraw.line(n.getRect().xmin(), p.y(), n.getRect().xmax(), p.y());
            }

        }
    }

    /**
     * all points that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<>();
        Node next = root;
        checkIntersect(next, rect, queue);
        return queue;
    }

    private void checkIntersect(Node next, RectHV rect, Queue<Point2D> q) {
        if (next == null) return;
        if (rect.contains(next.getP()))
            q.enqueue(next.getP());

        if (next.isIntersectLeft(rect))
            //check left
            checkIntersect(next.getLb(), rect, q);

        if (next.isIntersectRight(rect))
            //check right
            checkIntersect(next.getRt(), rect, q);
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (this.size() == 0) return null;
        Node nearestNode = root;
        nearestNode = checkClosest(root, p, nearestNode);
        return nearestNode.getP();
    }

    private Node checkClosest(Node next, Point2D p, Node nearestNode) {
        if (next != null) {
            if (next.getP().distanceSquaredTo(p) < nearestNode.getP().distanceSquaredTo(p))
                nearestNode = next;
            Node last = next;

            //there must insert prioritise left or right check first
            if (next.getP().compareTo(p) > 0) {
                next = last.getLb();
                //if distance to next rect is shorter then nearest
                if (next != null && next.getRect().distanceSquaredTo(p) < nearestNode.getP().distanceSquaredTo(p)) {
                    // check left
                    nearestNode = checkClosest(next, p, nearestNode);
                }
                next = last.getRt();
                if (next != null && next.getRect().distanceSquaredTo(p) < nearestNode.getP().distanceSquaredTo(p)) {
                    //check right
                    nearestNode = checkClosest(next, p, nearestNode);
                }
            } else {
                next = last.getRt();
                if (next != null && next.getRect().distanceSquaredTo(p) < nearestNode.getP().distanceSquaredTo(p)) {
                    //check right
                    nearestNode = checkClosest(next, p, nearestNode);
                }
                next = last.getLb();
                //if distance to next rect is shorter then nearest
                if (next != null && next.getRect().distanceSquaredTo(p) < nearestNode.getP().distanceSquaredTo(p)) {
                    // check left
                    nearestNode = checkClosest(next, p, nearestNode);
                }
            }
        }
        return nearestNode;
    }

    private Iterable<Node> getAllSubTree(Node node) {
        Queue<Node> q = new Queue<>();
        inoder(node, q);
        return q;
    }

    private void inoder(Node x, Queue<Node> q) {
        if (x == null) return;
        inoder(x.getLb(), q);
        q.enqueue(x);
        inoder(x.getRt(), q);
    }

    private Node getNext(Node last, Point2D p) {
        Node next = last;
        if (next.level % 2 == 0)
            if (p.x() < next.getP().x()) {
                last = next;
                next = last.getLb();
            } else {
                last = next;
                next = last.getRt();
            }
        else if (p.y() < next.getP().y()) {
            last = next;
            next = last.getLb();
        } else {
            last = next;
            next = last.getRt();
        }
        return next;
    }

    private static class Node {
        private Point2D p;      // the point
        private int level;
        private Node parent;
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree


        public Node(Point2D p) {
            this.p = p;
            this.level = 0;
            this.parent = null;
            this.rect = new RectHV(0.0, 0.0, 1.0, 1.0);
            this.lb = null;
            this.rt = null;

        }

        public Node(Point2D p, Node parent) {
            this.p = p;
            this.level = parent.getLevel() + 1;
            this.parent = parent;

            RectHV rectNewNode = null;
            if ((parent.getLevel() % 2) == 0) {  //even levels
                //(if the point to be inserted has a smaller x-coordinate
                // than the point at the root, go left; otherwise go right)
                if (p.x() < parent.getP().x()) {
                    parent.setLb(this);
                    rectNewNode = new RectHV(parent.getRect().xmin()
                            , parent.getRect().ymin()
                            , parent.getP().x()
                            , parent.getRect().ymax());
                } else {
                    parent.setRt(this);
                    rectNewNode = new RectHV(parent.getP().x()
                            , parent.getRect().ymin()
                            , parent.getRect().xmax()
                            , parent.getRect().ymax());
                }
                //then at the next level
            } else {
                //if the point to be inserted has a smaller y-coordinate
                // than the point in the node, go left; otherwise go right
                if (p.y() < parent.getP().y()) {
                    parent.setLb(this);
                    rectNewNode = new RectHV(parent.getRect().xmin()
                            , parent.getRect().ymin()
                            , parent.getRect().xmax()
                            , parent.getP().y());
                } else {
                    parent.setRt(this);
                    rectNewNode = new RectHV(parent.getRect().xmin()
                            , parent.getP().y()
                            , parent.getRect().xmax()
                            , parent.getRect().ymax());
                }
            }
            this.rect = rectNewNode;
        }

        public Node getLb() {
            return lb;
        }

        public void setLb(Node lb) {
            this.lb = lb;
        }

        public Node getRt() {
            return rt;
        }

        public void setRt(Node rt) {
            this.rt = rt;
        }

        public RectHV getRect() {
            return rect;
        }

        public void setRect(RectHV rect) {
            this.rect = rect;
        }

        public int getLevel() {
            return level;
        }

        public Point2D getP() {
            return p;
        }

        public Node getParent() {
            return parent;
        }

        public boolean isIntersectLeft(RectHV that) {
            return this.getLeftPart().intersects(that);
        }

        public boolean isIntersectRight(RectHV that) {
            return this.getRightPart().intersects(that);
        }

        public boolean isContainLeft(Point2D that) {
            return this.getLeftPart().contains(that);
        }

        public boolean isContainRight(Point2D that) {
            return this.getRightPart().contains(that);
        }

        private RectHV getLeftPart() {
            RectHV result;
            if (this.level % 2 == 0) {
                result = new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax());
            } else {
                result = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
            }
            return result;
        }

        private RectHV getRightPart() {
            RectHV result;
            if (this.level % 2 == 0) {
                result = new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax());
            } else {
                result = new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
            }
            return result;
        }
    }

    /**
     * unit testing of the methods (optional)
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}