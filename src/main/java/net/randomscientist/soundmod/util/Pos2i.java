package net.randomscientist.soundmod.util;

public class Pos2i {
    public int x;
    public int y;
    public Pos2i(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public Pos2i sub(Pos2i a) {
        return new Pos2i(this.x-a.getX(),this.y-a.getY());
    }
    public Pos2i add(Pos2i a) {
        return new Pos2i(this.x+a.getX(),this.y+a.getY());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getZ() {
        return this.y;
    }
    public void setZ(int z) {
        this.y = z;
    }

    @Override
    public boolean equals(Object obj) {
        Pos2i b = (Pos2i) obj;
        return b.getX()==this.x&&b.getY()==this.y;
    }
}