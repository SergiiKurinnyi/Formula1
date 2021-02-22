package si.kurinnyi.formula1.columns;

import java.util.function.Supplier;

public interface ColumnSupplier extends Supplier<Column> {

    ColumnType getType();

}
