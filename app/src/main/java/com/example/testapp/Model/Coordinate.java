package com.example.testapp.Model;

public class Coordinate{
    double la;
    double lo;

    public Coordinate(double la, double lo) {
        this.la = la;
        this.lo = lo;
    }
    public double getLa() {
        return la;
    }
    public void setLa(double la) {
        this.la = la;
    }
    public double getLo() {
        return lo;
    }
    public void setLo(double lo) {
        this.lo = lo;
    }
}
