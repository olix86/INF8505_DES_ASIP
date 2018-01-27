# INF8505_DES_ASIP

## Similar research

Fully pipelined DES on FPGA
https://www.cl.cam.ac.uk/~rnc1/descrack/DEScracker.html


Ajout d'instruction pour RISC-V

- https://quasilyte.github.io/blog/post/riscv32-custom-instruction-and-its-simulation/

## Tools

Simulateur

- Spike https://github.com/riscv/riscv-isa-sim



## Generating the rocket core (tested on debian sid 27-01-2018)
Étapes : 


1. Install riscv-tools

https://github.com/riscv/riscv-tools/blob/master/README.md

    #Setup directory
    mkdir riskv_proj
    cd riskv_proj
    export TOP=$(pwd)
    
    #Clone the git repo
    git clone https://github.com/riscv/riscv-tools.git
    cd $TOP/riscv-tools
    git submodule update --init --recursive
    
    # Install required tools
    sudo apt install autoconf automake autotools-dev curl device-tree-compiler libmpc-dev libmpfr-dev libgmp-dev gawk build-essential bison flex texinfo gperf libtool patchutils bc zlib1g-dev
    
    #Start the build
    export RISCV=$TOP/riscv
    export PATH=$PATH:$RISCV/bin
    ./build.sh
    
    #Test the toolchain
    cd $TOP
    echo -e '#include <stdio.h>\n int main(void) { printf("Hello world!\\n"); return 0; }' > hello.c
    riscv64-unknown-elf-gcc -o hello hello.c
    spike pk hello
    
    #Should see "hello world"
    
    
Environ 1h30 sur mon laptop

2. Rocket chip generator

https://github.com/freechipsproject/rocket-chip#how

JDE _and_ JDE are required, even if the README only mentions JDE

    sudo apt install default-jre default-jdk


3. Rocket chip for FPGA

https://github.com/ucb-bar/fpga-zynq#bitstream
