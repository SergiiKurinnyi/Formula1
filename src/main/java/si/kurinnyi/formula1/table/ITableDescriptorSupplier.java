package si.kurinnyi.formula1.table;

import java.util.function.Supplier;

public interface ITableDescriptorSupplier extends Supplier<TableDescriptor> {

    TableType getType();

}
