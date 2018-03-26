#include "rocc.h"

static inline unsigned long ip(unsigned long input_block)
{
	unsigned long output_block;
	//asm volatile ("fence");
	ROCC_INSTRUCTION_DS(0, output_block, input_block, 0);
	return output_block;
}

static inline unsigned long fp(unsigned long input_block)
{
	unsigned long output_block;
	//asm volatile ("fence");
	ROCC_INSTRUCTION_DS(1, output_block, input_block, 0);
	return output_block;
}

static inline unsigned long exp(unsigned long input_block)
{
	unsigned long output_block;
	//asm volatile ("fence");
	ROCC_INSTRUCTION_DS(2, output_block, input_block, 0);
	return output_block;
}

static inline unsigned long perm(unsigned long input_block)
{
	unsigned long output_block;
	//asm volatile ("fence");
	ROCC_INSTRUCTION_DS(3, output_block, input_block, 0);
	return output_block;
}

int main(void)
{
	unsigned long input_block = 0xF000000000000000;
	unsigned long output_block;
    unsigned long modified_block;
    
	/*for(int i=0; i < 10; i++)
    {
        output_block = ip(modified_block);
        input_block = output_block;
    }
	return output_block%10; */
    
    for(int i=0; i < 10; i++)
    {
        output_block = ip(input_block);
        output_block = fp(input_block);
        output_block = exp(input_block);
        output_block = perm(input_block);
    }
    
    return 0;

}
