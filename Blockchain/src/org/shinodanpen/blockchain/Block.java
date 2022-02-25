package org.shinodanpen.blockchain;

import java.util.Date;

public class Block {
    private String hash;
    private String prevHash;
    private String blockData;
    private long timestamp;
    private int nonce;

    public Block(String blockData, String prevHash) {

        this.prevHash = prevHash;
        this.blockData = blockData;
        this.timestamp =  new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return Encrypter.encryptSHA256(
                prevHash
                +   Long.toString(timestamp)
                +   Integer.toString(nonce)
                +   blockData);
    }

    public String mineBlock(int prefix){
        String prefixString = new String(new char[prefix]).replace('\0', 'e');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block has been mined.");
        System.out.println("Hash: " + hash);
        return hash;
    }

    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getBlockData() {
        return blockData;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }
}
