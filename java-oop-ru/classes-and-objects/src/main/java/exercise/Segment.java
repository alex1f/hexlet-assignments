package exercise;

// BEGIN
public class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getBeginPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint(){
        int midX = (start.getX() + end.getX()) / 2;
        int midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }
}
// END
