#include "rocc.h"

static inline unsigned long ip(unsigned long input_block)
{
	unsigned long output_block;
	//asm volatile ("fence");
	ROCC_INSTRUCTION_DS(2, output_block, input_block, 0);
	return output_block;
}



int main(void)
{
	unsigned long input_block = 0xF000000000000000;
    volatile unsigned long output_block;
    //unsigned long modified_block;
    
	for(int i=0; i < 1; i++)
    {
        output_block = ip(input_block);
        //input_block = output_block;
    }
	//return output_block%10;
	return 0;

}
