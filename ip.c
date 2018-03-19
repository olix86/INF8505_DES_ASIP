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
	unsigned long output_block;
	
	output_block = ip(input_block);

	return 0;

}
