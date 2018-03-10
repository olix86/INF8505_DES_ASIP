# INF8505 ASIP project


## Installation 
How to install the RISCV Rocket Core project template from scratch

### Required downloads
    # Setup directory
    mkdir riscv_proj
    cd riscv_proj
    
    
    # Clone the git repo
    # Roughly 3GB to download
    git clone https://github.com/ucb-bar/project-template.git
    cd project-template/
    git submodule update --init --recursive
    cd rocket-chip/
    export TOP=$(pwd)
    cd $TOP/riscv-tools
    
    # Install required tools
    # JRE and JDK are required, even if the README only mentions JRE
    sudo apt install autoconf automake autotools-dev curl device-tree-compiler libmpc-dev libmpfr-dev libgmp-dev gawk build-essential bison flex texinfo gperf libtool patchutils bc zlib1g-dev default-jre default-jdk
    
### Building riscv-tools

    # Takes rougly 8GB after compilation
    export RISCV=$TOP/riscv
    export PATH=$PATH:$RISCV/bin

    # Start the build
    ./build.sh

    # Test the toolchain
    cd $TOP
    echo -e '#include <stdio.h>\n int main(void) { printf("Hello world!\\n"); return 0; }' > hello.c
    riscv64-unknown-elf-gcc -o hello hello.c
    spike pk hello
    #You should see "hello world"

    # Build tests
    cd $TOP/riscv-tools/riscv-tests
    autoconf
    ./configure --prefix=$RISCV/target
    make
    
### Building Verilator and the processor configuration to simulate    
    # Build the processor configuration emulator binary
    # Rougly 9GB after compilation
    cd $TOP
    cd ../verisim
    make
    
    #Test it with a RISCV binary
    ./simulator-example-DefaultExampleConfig $RISCV/riscv64-unknown-elf/share/riscv-tests/isa/rv64ui-p-simple +verbose

    
## Adding custom instructions with RoccExampleConfig

    make  CONFIG=RoccExampleConfig

