import java.util.List;
public class Nodes {
	// Storing data in the node
	private final List<byte[]> dataBlock;

	// Constructor initializing the data
	public Nodes(final List<byte[]> dataBlock) {
		this.dataBlock = dataBlock;
	}

	// Returning the data block
	public List<byte[]> getDataBlock() {
		return (dataBlock);
	}

	// For internally using to convert byte to a HEX string
	private String toHexString(final byte[] array) {
		final StringBuilder str = new StringBuilder();
		str.append("[");
		boolean isFirst = true;
		for(int i = 0 ; i < array.length ; i++) {
			final byte b = array[i];
			if (isFirst)
				isFirst = false;
			else
				str.append(",");
			final int hiVal = (b & 0xF0) >> 4;
	        final int loVal = b & 0x0F;
	        str.append((char) ('0' + (hiVal + (hiVal / 10 * 7))));
	        str.append((char) ('0' + (loVal + (loVal / 10 * 7))));
		}
		str.append("]");
		return(str.toString());
	}

	// For printing the nodes
	public String toString() {
		final StringBuilder str = new StringBuilder();
		for(byte[] block: dataBlock)
			str.append(toHexString(block));
		return(str.toString());
	}
	
}