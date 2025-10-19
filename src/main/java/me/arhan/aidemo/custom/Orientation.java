package me.arhan.aidemo.custom;

enum Orientation {
    COLLINEAR, CLOCKWISE, COUNTERCLOCKWISE;

    public static Orientation getOrientation(Coordinate p, Coordinate q, Coordinate r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return COLLINEAR;
        return (val > 0) ? CLOCKWISE : COUNTERCLOCKWISE;
    }

}