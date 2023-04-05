# Combinatorial_Mutagenesis_Simulator
Combinatorial_Mutagenesis_Simulator is a simulator that is used to simulate the sampling requirements for 
comibnatorial gene variant libraries using user-specified mutagenesis schemes, number of sites to mutate, and 
percent coverage of all possible sequences in the library.

# Installation
Combinatorial_Mutagenesis_Simulator runs on Java 11.

To install Combinatorial_Mutagenesis_Simulator, first git clone the Combinatorial_Mutagenesis_Simulator repository.

```
git clone https://github.com/jingliu-ut/Combinatorial_Mutagenesis_Simulator.git
```
Then, from the Combinatorial_Mutagenesis_Simulator, compile the java files.

```
javac Simulator.java
```

Next, run the program by the following commands which contains five arguments. The first argument asks for an 
integer which specifies the number of sites to mutate. The second arguments specifies whether this is a dna or aa 
simulation. The third argument sets the mutagenesis scheme. The fourth argument specify the percent coverage aimed 
to achive. And the last argument specifies the replicate number. **The arguments must be typed in this order**.

```
java Simulator <number of sites to mutate> <dna or aa> <NNK or NNB> <percent coverage> <replicate number>
```

Example command for a dna simulation of 2 sites using NNK mutagenesis scheme and percent coverage is set to 99 and 
replicate number is 1.

```
java Simulator 2 dna NNK 99 1
```

An `Outputs` directory will be created. This folder will contain a TXT file contains all posible unique sequences, 
and two TXT files for the simulation results, one contains nucleotide sequences, one contains protein sequences.