## Introduction

Merkle trees are non-linear binary tree data structure where the non-leaf node is defined as the hash of its respective child nodes.

The leaf nodes are technically the bottom most nodes.

The tree is constructed bottom-up.

![Merkle Tree](/images/merkle-tree.webp)

In the above image, we can see 4 transactions have taken place in the block, namely **X**, **Y**, **Z** and **W**. The transactions are then hashed and stored in leaf nodes.

Once hashed, these nodes are combined to form combined hash of **XY** and **ZW** respectively.

This process continues till we reach the Merkle Root or root hash.

A transaction can be verified if the previous hash values are verifiable.

If we find any difference in hash values then we can detect it in O(log N) time complexity.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.