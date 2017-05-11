/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

public class RandomDoubleGenerator {
    private double max;
    private double min;

    public RandomDoubleGenerator(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double nextValue() {
        return this.min + Math.random() * (this.max - this.min);
    }

    public String toString() {
        return "RandomDoubleGenerator with range " + this.min + " to " + this.max;
    }
}

