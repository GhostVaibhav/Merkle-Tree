import java.security.MessageDigest;
import java.util.List;
public class MerkleTree {
    // Data members of the Merkle tree
	private MerkleTree leftTree = null;
	private MerkleTree rightTree = null;
	private Nodes leftLeaf = null;
	private Nodes rightLeaf = null;

    // For storing the digest of the tree
	private byte[] digest;
	private final MessageDigest hashAlgo;

    // For internally computing the digest of a particular node
	private byte[] digest(Nodes leaf) {
		final List<byte[]> dataBlock = leaf.getDataBlock();
		final int noOfBlocks = dataBlock.size();
		for (int i = 0 ; i < noOfBlocks - 1 ; i++)
			hashAlgo.update(dataBlock.get(i));
		digest = hashAlgo.digest(dataBlock.get(noOfBlocks-1));
		return (digest);
	}

    // Initializing the hashing algorithm to be used for computing the hash of every node
	public MerkleTree(MessageDigest hashAlgo) {
		this.hashAlgo = hashAlgo;
	}

    // Adding two Merkle trees to get a single tree
	public void add(final MerkleTree leftTree, final MerkleTree rightTree) {
		this.leftTree = leftTree;
		this.rightTree = rightTree;
		hashAlgo.update(leftTree.digest());
		digest = hashAlgo.digest(rightTree.digest());
	}

    // Adding two leaf nodes to get a single node
	public void add(final Nodes leftLeaf, final Nodes rightLeaf) {
		this.leftLeaf = leftLeaf;
		this.rightLeaf = rightLeaf;
		hashAlgo.update(digest(leftLeaf));
		digest = hashAlgo.digest(digest(rightLeaf));
	}

    // Getting the digest
	public byte[] digest() {
		return digest;
	}

    // For internally converting the "byte" array of a node to HEX format
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

    // Core function for pretty-printing the Merkle tree
	private void prettyPrint(final int indent) {
		for(int i = 0 ; i < indent ; i++)
			System.out.print(" ");
		System.out.println("Node digest: " + toHexString(digest()));
		if (rightLeaf != null && leftLeaf != null) {
			for(int i = 0 ; i < indent + 1 ; i++)
				System.out.print(" ");
			System.out.println("Left leaf: " + rightLeaf.toString());
            System.out.println("Right leaf: " + leftLeaf.toString());
		}
		else if (rightTree != null && leftTree != null) {
			rightTree.prettyPrint(indent + 1);
			leftTree.prettyPrint(indent + 1);
		}
		else
			System.out.println("Empty tree");
	}

    // For calling the core function for pretty-printing the Merkle tree
	public void prettyPrint() {
		prettyPrint(0);
	}
}