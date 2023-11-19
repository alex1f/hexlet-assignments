package exercise;

// BEGIN
public class Flat implements Home{
    private final double area;
    private final double balconyArea;
    private final int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home other) {
        double thisArea = this.getArea();
        double  otherArea = other.getArea();
        return Double.compare(thisArea, otherArea);
    }

    public String toString(){
        return String.format("Квартира площадью %.1f метров на %d этаже", this.getArea(), floor);
    }
}
// END
