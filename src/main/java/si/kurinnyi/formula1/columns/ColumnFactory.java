package si.kurinnyi.formula1.columns;

import si.kurinnyi.formula1.columns.impl.*;

import java.util.*;

public class ColumnFactory {

    private final Map<ColumnType, ColumnSupplier> columnMap = new HashMap<>();

    public ColumnFactory() {

        addColumn(new ColumnSupplier() {

            @Override
            public ColumnType getType() {
                return ColumnType.ROW_NUM;
            }

            @Override
            public Column get() {
                return new RowNumColumn();
            }
        });

        addColumn(new ColumnSupplier() {

            @Override
            public ColumnType getType() {
                return ColumnType.NAME;
            }

            @Override
            public Column get() {
                return new NameColumn();
            }
        });

        addColumn(new ColumnSupplier() {

            @Override
            public ColumnType getType() {
                return ColumnType.TEAM;
            }

            @Override
            public Column get() {
                return new TeamColumn();
            }
        });

        addColumn(new ColumnSupplier() {

            @Override
            public ColumnType getType() {
                return ColumnType.BEST_LAP;
            }

            @Override
            public Column get() {
                return new BestLapColumn();
            }
        });

        addColumn(new ColumnSupplier() {

            @Override
            public ColumnType getType() {
                return ColumnType.LAP_COUNT;
            }

            @Override
            public Column get() {
                return new LapCountColumn();
            }
        });

        addColumn(new ColumnSupplier() {

            @Override
            public ColumnType getType() {
                return ColumnType.AVG_LAP_TIME;
            }

            @Override
            public Column get() {
                return new AvgLapTimeColumn();
            }
        });

        addColumn(new ColumnSupplier() {

            @Override
            public ColumnType getType() {
                return ColumnType.TOTAL_TIME;
            }

            @Override
            public Column get() {
                return new AllLapsTimeColumn();
            }
        });
    }

    private void addColumn(ColumnSupplier columnSupplier) {
        columnMap.put(columnSupplier.getType(), columnSupplier);
    }

    public Column get(ColumnType columnType) {
        if (columnMap.containsKey(columnType)) {
            return columnMap.get(columnType).get();
        } else {
            throw new IllegalArgumentException();
        }
    }

}
