public class ClassFields {
    private final int a = 1;
    @Save public  int b = 2;
    @Save private  int c = 3;
    private final String aS="one";
    @Save private  String bS="two";
    @Save private  String cS="three";

    @Override
    public String toString() {
        return "ClassFields{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", aS='" + aS + '\'' +
                ", bS='" + bS + '\'' +
                ", cS='" + cS + '\'' +
                '}';
    }
}
