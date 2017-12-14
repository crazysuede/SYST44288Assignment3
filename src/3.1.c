//By Matthew Siuda (crazysuede)
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

double average = 0;//variable for double
int max = 0;//variable for max
int min;//variable for min

int count = 0;

void * findAverage(void * num);
void * findMax(void * num);
void * findMin(void * num);

int main (int argc, char *argv[])//argc is # of arguments/argv is array of arguments
{
	pthread_t thread1,thread2,thread3;

	int num [argc - 1];//variable to hold numbers
	int x;//counter variable

	count = argc - 1;

	if (argc < 2)//if there isnt any numbers inputted
	{
		puts("Missing arguments.");
		return 0;
	}
	for (x = 1; x <= count; x++)
	{
		num[x] = atoi(argv[x]);//converts the command line argument to a number
		printf("value at position %d is %d\n", x, num[x]);//debug to see numbers being read correctly
	}

	if (pthread_create(&thread1, NULL, findAverage, num) != 0)//pthread returns 0 if it suceeds
	{
		printf("Error in thread1");
		return 0;
	}

	if (pthread_create(&thread2, NULL, findMax, num) != 0)
	{
		printf("Error in thread2");
		return 0;
	}

	if (pthread_create(&thread3, NULL, findMin, num) != 0)
	{
		printf("Error in thread3");
		return 0;
	}

	if (pthread_join(thread1, NULL) != 0)
	{
		printf("Error joining thread1");
		return 0;
	}

	if (pthread_join(thread2, NULL) != 0)
	{
		printf("Error joining thread2");
	}

	if (pthread_join(thread3, NULL) != 0)
	{
		printf("Error joining thread3");
	}
	printf("Average: %.2f\n", average);
	printf("Max: %.2d\n", max);
	printf("Min: %.2d\n", min);


}
void * findAverage(void * num)
{
	puts("findAverage Launched.");//check if thread is launching
	int sum = 0;//local varaible for sum
	int x;//counter variable

	int *temp = (int *)num;//load passed int array into local variable

	for (x = 0; x < count; x++)//from first number in array to last
	{
		//printf("value of temp[x]: %d\n", temp[x+1]);//DEBUG, see whats in the array 
		sum += temp[x+1];//sum the numbers
		//printf("findAverage Thread: sum is currently %d\n", sum);//DEBUG check sum
	}
	average = (double)sum / count;//change average in global
}

void * findMax(void * num)
{
	puts("findMax Launched.");

	int x;//counter variable

	int *temp = (int *)num;//create local number variable

	for (x = 0; x < count; x++)//go thru all numbers
	{
		if (temp[x+1] > max)//if any of the numbers is greater than the current max
		{
			max = temp[x+1];//set new max
		}
	}
	puts("findMax Finished");
}

void * findMin(void * num)
{
	puts("findMin Launched.");

	int x;//counter variable

	int *temp = (int *)num;//create local variable

	min = temp[x+1];//set the first number is current min

	for (x = 0; x < count; x++)//go thru loop
	{
		if (temp[x+1] < min)//if any are less then min
		{
			min = temp[x+1];//set new min
		}
	}
	puts("findMin Finished");
}
