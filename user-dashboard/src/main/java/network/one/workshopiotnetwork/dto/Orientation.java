package network.one.workshopiotnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orientation {

    @JsonProperty
    private Double x;

    @JsonProperty
    private Double y;

    @JsonProperty
    private Double z;

    @JsonProperty
    private Double w;

    public Double getX() {
        return x;
    }

    public void setX(final Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(final Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(final Double z) {
        this.z = z;
    }

    public Double getW() {
        return w;
    }

    public void setW(final Double w) {
        this.w = w;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((w == null) ? 0 : w.hashCode());
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        result = prime * result + ((z == null) ? 0 : z.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Orientation other = (Orientation) obj;
        if (w == null) {
            if (other.w != null)
                return false;
        } else if (!w.equals(other.w))
            return false;
        if (x == null) {
            if (other.x != null)
                return false;
        } else if (!x.equals(other.x))
            return false;
        if (y == null) {
            if (other.y != null)
                return false;
        } else if (!y.equals(other.y))
            return false;
        if (z == null) {
            if (other.z != null)
                return false;
        } else if (!z.equals(other.z))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Orientation [w=" + w + ", x=" + x + ", y=" + y + ", z=" + z + "]";
    }

}