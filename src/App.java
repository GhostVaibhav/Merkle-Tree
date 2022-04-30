import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // For using in computing the hash of the blocks
        MessageDigest hashAlgo = null;
        hashAlgo = MessageDigest.getInstance("SHA-256");

        // Defining random blocks of memory
		final byte[] blockA = {(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04};
		final byte[] blockB = {(byte) 0xae, (byte) 0x45, (byte) 0x98, (byte) 0xff};
		final byte[] blockC = {(byte) 0x5f, (byte) 0xd3, (byte) 0xcc, (byte) 0xe1};
		final byte[] blockD = {(byte) 0xcb, (byte) 0xbc, (byte) 0xc4, (byte) 0xe2};
		final byte[] blockE = {(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04};
		final byte[] blockF = {(byte) 0xae, (byte) 0x45, (byte) 0x98, (byte) 0xff};
		final byte[] blockG = {(byte) 0x5f, (byte) 0xd3, (byte) 0xcc, (byte) 0xe1};
		final byte[] blockH = {(byte) 0xcb, (byte) 0xbc, (byte) 0xc4, (byte) 0xe2};
        
        // Computing the next layer of Merkle Tree
		final List<byte[]> blocksAandB = new ArrayList<byte[]>();
		blocksAandB.add(blockA);
		blocksAandB.add(blockB);
		final List<byte[]> blocksCandD = new ArrayList<byte[]>();
		blocksCandD.add(blockC);
		blocksCandD.add(blockD);
		final List<byte[]> blocksEandF = new ArrayList<byte[]>();
		blocksEandF.add(blockE);
		blocksEandF.add(blockF);
		final List<byte[]> blocksGandH = new ArrayList<byte[]>();
		blocksGandH.add(blockG);
		blocksGandH.add(blockH);

        // Computing the next layer
		final Nodes leaf1 = new Nodes(blocksAandB);
		final Nodes leaf2 = new Nodes(blocksCandD);
		final Nodes leaf3 = new Nodes(blocksEandF);
		final Nodes leaf4 = new Nodes(blocksGandH);

        // Computing the next layer
		final MerkleTree blocksAandBandCandD = new MerkleTree(hashAlgo);
		blocksAandBandCandD.add(leaf1, leaf2);
		final MerkleTree blocksEandFandGandH = new MerkleTree(hashAlgo);
		blocksEandFandGandH.add(leaf3, leaf4);

        // Computing the Merkle root
		final MerkleTree merkleRoot = new MerkleTree(hashAlgo);
		merkleRoot.add(blocksAandBandCandD, blocksEandFandGandH);
        merkleRoot.prettyPrint();
    }
}
