#include "rocc.h"

int ip[64] = 	{58, 50, 42, 34, 26, 18, 10, 2,
				60, 52, 44, 36, 28, 20, 12, 4,
				62, 54, 46, 38, 30, 22, 14, 6,
				64, 56, 48, 40, 32, 24, 16, 8,
				57, 49, 41, 33, 25, 17,  9, 1,
				59, 51, 43, 35, 27, 19, 11, 3,
				61, 53, 45, 37, 29, 21, 13, 5,
				63, 55, 47, 39, 31, 23, 15, 7};

int main(void)
{
	unsigned char input_block[8] = {0xF0,0,0,0,0,0,0,0};
	unsigned char output_block[8];
	
	int i;
	for (i = 0; i < 64; i++) {
		int shift_size;
		unsigned char shift_byte;
		shift_size = ip[i];
		shift_byte = 0x80 >> ((shift_size - 1)%8);
		shift_byte &= input_block[(shift_size - 1)/8];
		shift_byte <<= ((shift_size - 1)%8);

		output_block[i/8] |= (shift_byte >> i%8);
	}

	return 0;

}
