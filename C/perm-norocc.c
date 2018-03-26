#include "rocc.h"

int ip[32] = 	{16 ,7 ,20 ,21 ,29 ,12 ,28 ,17,
                1 ,15 ,23 ,26 ,5 ,18 ,31 ,10,
                2 ,8 ,24 ,14 ,32 ,27 ,3 ,9,
                19 ,13 ,30 ,6 ,22 ,11 ,4 ,25};

int main(void)
{
	unsigned char input_block[8] = {0xF0,0,0,0,0,0,0,0};
	volatile unsigned char output_block[8];
    //unsigned long modified_block;
        
	//int i;
    for(int i=0; i < 1000; i++)
    {
        //modified_block = input_block + i;
        for (int j = 0; j < 32; j++) {
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

