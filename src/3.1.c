#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

double average;//variable for double
int max;//variable for max
int min;//variable for min

int main (int argc, char *argv[])//argc is # of arguments/argv is array of arguments
{
	int num [argc - 1];//variable to hold numbers
	int x;//counter variable
	for (x = 1; x < argc; x++)
	{
		num[x] = atoi(argv[x]);//converts the command line argument to a number
		printf("value at position %d is %d\n", x, num[x]);//debug to see numbers being read correctly
	}
}
