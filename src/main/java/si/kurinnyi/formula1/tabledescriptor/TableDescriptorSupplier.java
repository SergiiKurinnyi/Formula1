package si.kurinnyi.formula1.tabledescriptor;

import java.util.function.Supplier;

public interface TableDescriptorSupplier extends Supplier<TableDescriptor> {

    TableType getType();

}
