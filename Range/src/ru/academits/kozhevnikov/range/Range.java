package ru.academits.kozhevnikov.range;

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

    public double getBoundaryDifference() {
        return to - from;
    }

    public boolean isInside(double numberToCheck) {
        return numberToCheck >= from && numberToCheck <= to;
    }

    public Range getIntersection(Range range) {
        if (to <= range.from || range.to <= from) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range2) {
        if (to < range2.from || range2.to < from) {
            return new Range[]{new Range(from, to), new Range(range2.from, range2.to)};
        }

        return new Range[]{new Range(Math.min(from, range2.from), Math.max(to, range2.to))};
    }

    public static String toString(Range[] ranges) {
        if (ranges == null) {
            return "null";
        }

        int iMax = ranges.length - 1;

        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();

        b.append('[');

        for (int i = 0; ; i++) {
            b.append(ranges[i].from);
            b.append(", ");
            b.append(ranges[i].to);

            if (i == iMax) {
                return b.append(']').toString();
            }

            b.append("; ");
        }
    }

    public Range[] getSubtraction(Range range) {
        if (from >= range.from && to <= range.to) {
            return null;
        }

        if (to <= range.from || range.to <= from) {
            return new Range[]{new Range(from, to)};
        }

        if (from >= range.from) {
            return new Range[]{new Range(range.to, to)};
        }

        if (to <= range.to) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{new Range(from, range.from), new Range(range.to, to)};
    }
}