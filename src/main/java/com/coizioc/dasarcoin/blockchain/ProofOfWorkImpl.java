package com.coizioc.dasarcoin.blockchain;

import com.coizioc.dasarcoin.util.ByteUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;

public class ProofOfWorkImpl implements ProofOfWork {
    private final int targetBits;
    private final BigInteger target;

    public ProofOfWorkImpl(int targetBits) {
        this.targetBits = targetBits;
        this.target = BigInteger.ONE.shiftLeft(256 - targetBits);
    }

    @Override
    public Result run(Block block) {
        byte[] hash = new byte[32];
        long nonce = 0;

        while (nonce < Long.MAX_VALUE) {
            byte[] data = prepareData(block, nonce);
            hash = DigestUtils.sha256(data);

            if (isValidHash(hash)) {
                break;
            } else {
                nonce++;
            }
        }

        return new Result(nonce, hash);
    }

    @Override
    public boolean validate(Block block) {
        byte[] data = prepareData(block, block.getNonce());
        byte[] hash = DigestUtils.sha256(data);

        return isValidHash(hash);
    }

    private byte[] prepareData(Block block, long nonce) {
        return ByteUtils.join(
                block.getPrevBlockHash(),
                block.getData(),
                Long.toHexString(block.getTimestamp()).getBytes(),
                Integer.toHexString(targetBits).getBytes(),
                Long.toHexString(nonce).getBytes()
        );
    }

    private boolean isValidHash(byte[] hash) {
        BigInteger hashInt = new BigInteger(1, hash);
        return hashInt.compareTo(target) < 0;
    }

}
