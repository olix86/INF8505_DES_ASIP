#include "rocc.h"

int ip[64] = 	{40,8,	48,	16,	56,	24,	64,	32,
                39,	7,	47,	15,	55,	23,	63,	31,
                38,	6,	46,	14,	54,	22,	62,	30,
                37,	5,	45,	13,	53,	21,	61,	29,
                36,	4,	44,	12,	52,	20,	60,	28,
                35,	3,	43,	11,	51,	19,	59,	27,
                34,	2,	42,	10,	50,	18,	58,	26,
                33,	1,	41,	9,	49,	17,	57,	25};

int main(void)
{
	unsigned char input_block[8] = {0xF0,0,0,0,0,0,0,0};
	volatile unsigned char output_block[8];
    //unsigned long modified_block;
        
	//int i;
    for(int i=0; i < 1000; i++)
    {
        //modified_block = input_block + i;
        for (int j = 0; j < 64; j++) {
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

