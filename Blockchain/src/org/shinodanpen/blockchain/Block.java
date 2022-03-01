package org.shinodanpen.blockchain;

import java.util.Date;

public class Block {
    private String prevHash;
    private String blockData;
    private long timestamp;
    private int nonce;

    public Block(String blockData, String prevHash) {

        this.prevHash = prevHash;
        this.blockData = blockData;
        this.timestamp =  new Date().getTime();
        calculateHash();
    }

    public String calculateHash(){
        return Encrypter.encryptSHA256(
                prevHash
                +   Long.toString(timestamp)
                +   Integer.toString(nonce)
                +   blockData);
    }

    public String mineBlock(int prefix){
        String hash = calculateHash();
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
        return calculateHash();
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getBlockData() {
        return blockData;
    }

    public void setBlockData(String blockData) {
        this.blockData = blockData;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }
}
