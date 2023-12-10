package com.ionutciuta.datastructures;

import java.util.List;
import java.util.function.Function;

public record Matrix<T>(List<List<T>> data) {
    public int rowCount() {
        return data.size();
    }

    public int colCount() {
        return data.get(0).size();
    }

    public T valAt(int row, int col) {
        return data.get(row).get(col);
    }

    public boolean hasValueAt(int row, int col) {
        return row >= 0 && row < rowCount()
                && col >= 0 && col < colCount();
    }

    @Override
    public String toString() {
        return print(T::toString);
    }

    public String print(Function<T, String> cellDisplayFunction) {
        var sb = new StringBuilder();
        for (var line : data) {
            var lineSb = new StringBuilder();
            for (T cell : line) {
                lineSb.append(cellDisplayFunction.apply(cell)).append(" ");
            }
            lineSb.append("\n");
            sb.append(lineSb);
        }
        return sb.toString();
    }
}
