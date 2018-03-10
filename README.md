# INF8505_DES_ASIP

## Similar research

Fully pipelined DES on FPGA
- https://www.cl.cam.ac.uk/~rnc1/descrack/DEScracker.html


Ajout d'instruction pour RISC-V

- https://quasilyte.github.io/blog/post/riscv32-custom-instruction-and-its-simulation/

## Tools

Simulateur

- Spike https://github.com/riscv/riscv-isa-sim



## Generating the rocket core (tested on debian sid 27-01-2018)
Steps


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
    
    
Takes roughly 1h30 on a 7th gen i5 laptop

2. Rocket chip generator

https://github.com/freechipsproject/rocket-chip#how

    #JDE _and_ JDE are required, even if the README only mentions JDE
    sudo apt install default-jre default-jdk
    
    #Install rocket chip generator
    cd $TOP
    git clone https://github.com/ucb-bar/rocket-chip.git
    cd rocket-chip
    export ROCKETCHIP=`pwd`
    git submodule update --init
    cd riscv-tools
    git submodule update --init --recursive riscv-tests

    #Generate verilog for FPGA
    cd $ROCKETCHIP/vsim
    make verilog CONFIG=DefaultFPGAConfig

    
3. Rocket chip for FPGA

https://github.com/ucb-bar/fpga-zynq#bitstream

    cd $TOP
    git clone https://github.com/ucb-bar/fpga-zynq.git
    cd fpga-zynq/zedboard
    make init-submodules
    export ROCKET_DIR=$ROCKETCHIP
    
    
    #Build verilog
    make rocket
    
    
    
    
    
Project Template
----------------

    #Setup directory
    mkdir riscv_proj
    cd riscv_proj
    
    
    #Clone the git repo
    git clone https://github.com/ucb-bar/project-template.git
    cd project-template/
    git submodule update --init --recursive
    cd rocket-chip/
    export TOP=$(pwd)
    cd $TOP/riscv-tools
    
    #Roughly 3GB
    
    
    # Install required tools
    sudo apt install autoconf automake autotools-dev curl device-tree-compiler libmpc-dev libmpfr-dev libgmp-dev gawk build-essential bison flex texinfo gperf libtool patchutils bc zlib1g-dev default-jre default-jdk
    
    #Start the build
    export RISCV=$TOP/riscv
    export PATH=$PATH:$RISCV/bin
    ./build.sh
    
    #Rougly 8GB
    
    #Test the toolchain
    cd $TOP
    echo -e '#include <stdio.h>\n int main(void) { printf("Hello world!\\n"); return 0; }' > hello.c
    riscv64-unknown-elf-gcc -o hello hello.c
    spike pk hello
    
    #Should see "hello world"

    #Build tests
    cd $TOP/riscv-tools/riscv-tests
    autoconf
    ./configure --prefix=$RISCV/target
    make
    
    cd $TOP
    cd ../verisim
    make
    ./simulator-example-DefaultExampleConfig $RISCV/riscv64-unknown-elf/share/riscv-tests/isa/rv64ui-p-simple
    
    #Erreur, remplacer les lignes CXXFLAGS et LDFLAGS dans Makefile
    CXXFLAGS := $(CXXFLAGS) -O1 -std=c++11 -I$(RISCV)/include -I$(base_dir)/rocket-chip/riscv/include -D__STDC_FORMAT_MACROS
    LDFLAGS := $(LDFLAGS) -L$(RISCV)/lib -Wl,-rpath,$(RISCV)/lib -L$(sim_dir) -L$(base_dir)/rocket-chip/riscv/lib -lfesvr -lpthread
    
    
    ./simulator-example-DefaultExampleConfig $RISCV/riscv64-unknown-elf/share/riscv-tests/isa/rv64ui-p-simple +verbose

    make  CONFIG=RoccExampleConfig

