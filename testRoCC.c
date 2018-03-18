#include "rocc.h"


static inline unsigned long test(unsigned long idx)
{
	unsigned long value;
	ROCC_INSTRUCTION_DSS(3, value, 0, idx, 0);
	return value;
}


//unsigned long data = 0x3421L;

int main(void)
{
	unsigned long result;

	result = test(0);

	if (result != 0)
    {
        return result;
    }

	result = test(2);

	if (result != 2)
    {
        return 2;
    }

	return 0;
}
