#include "rocc.h"

int ip[48] = 	{32,1,	2,	3,	4,	5,
                4,	5,	6,	7,	8,	9,
                8,	9,	10,	11,	12,	13,
                12,	13,	14,	15,	16,	17,
                16,	17,	18,	19,	20,	21,
                20,	21,	22,	23,	24,	25,
                24,	25,	26,	27,	28,	29,
                28,	29,	30,	31,	32,	1};

int main(void)
{
	unsigned char input_block[8] = {0xF0,0,0,0,0,0,0,0};
	volatile unsigned char output_block[8];
    //unsigned long modified_block;
        
	//int i;
    for(int i=0; i < 1; i++)
    {
        //modified_block = input_block + i;
        for (int j = 0; j < 48; j++) {
            int shift_size;
            unsigned char shift_byte;
            shift_size = ip[j];
            shift_byte = 0x80 >> ((shift_size - 1)%8);
            shift_byte &= input_block[(shift_size - 1)/8];
            shift_byte <<= ((shift_size - 1)%8);

            output_block[j/8] |= (shift_byte >> j%8);
        }
    }
	return 0;

}

