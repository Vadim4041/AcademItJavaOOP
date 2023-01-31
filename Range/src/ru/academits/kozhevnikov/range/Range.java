package ru.academits.kozhevnikov.range;

import java.util.Arrays;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getRange() {
        return to - from;
    }

    public boolean isInside(double numberToCheck) {
        return numberToCheck >= from && numberToCheck <= to;
    }

    public Range getIntersectionWith(Range range2) {
        if (to <= range2.from || range2.to <= from) {
            return null;
        }

        if (to <= range2.to && from <= range2.from) {
            return new Range(range2.from, to);
        }

        if (from <= range2.from) {
            return range2;
        }

        return new Range(from, to);
    }

    public Range[] getAdditionArray(Range range2) {
        return new Range[]{new Range(from, to), range2};
    }

    public Range getAdditionRange(Range range2) {
        return new Range(Math.min(from, range2.from), Math.max(to, range2.to));
    }

    public double getAdditionNumber(Range range2) {
        if (to == range2.from) {
            return to;
        }

        return from;
    }

    public void printAddition(Range range2) {
        if (to < range2.from || range2.to < from) {
            System.out.println(Arrays.toString(getAdditionArray(range2)));
        } else if (to == from && range2.to == range2.from && to == range2.to) {
            System.out.println(getAdditionNumber(range2));
        } else {
            System.out.println(getAdditionRange(range2).getFrom());
            System.out.println(getAdditionRange(range2).getTo());
        }
    }
}