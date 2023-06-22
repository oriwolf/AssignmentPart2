The Main class:

Not part of this assignment, only contains sanity checks.
For activating lease note that we have to change the access modifiers of the data structures from private to
public (I could have created a method for getting it, but since it's unnecessary for the program itself I
decided not doing it).


The OS class:

Regarding which function should be more efficient -
The awake method should be more efficient because it needs to be called every 1ms. Also, it holds
the responsibility for moving the running thread to the appropriate queue. This is a crucial aspect for the
proper execution of the program. In addition, If the method is not efficient, it can result in significant
overhead and negatively impact the overall execution of the program.

The awake() method:
As described in the last paragraph this function should be vert efficient. In addition, I was asked to
implement it such its time complexity will be O(1). There are 2 options 1)checking the first element and if
it's greater than zero subtract its remaining time by one. 2) iterating the sleeping jobs and removes each job
which is remaining time is zero. Since the number of these element is constant(an assumption) the running time
of the awake() function is O(1) - thanks to the implementation of the sleep() function and that way it
maintains the sleepingJobs queue as will be described later.

The sleep() method:
Due to the fact I couldn't use any time methods or any stimulating time data members I had to use another
technique to keep track of the jobs and their timing. The implementation relied on the fact that if the first
job in the queue waits for a certain amount of time, the subsequent job has to wait the time of the job before
it and the difference between the time of the job before it and its original sleeping time.
Understanding this principle helped me keep track of the jobs and prioritized the jobs in accord to their
sleeping time in real time. Using this understanding together with the fact that the awake function is
called every 1ms, I managed to stimulate the time without using time methods or static members which keeping
track of the time.
Since this method in the worst case would have to iterate the all jobs in the sleepingJobs LinkedList(queue),
it's time complexity is O(N), while N is the number of jobs in the sleepingJobs data structure at a given
moment.

Illustration of the maintenance of the sleepingJobs DS:
sleep(10)____ 10
sleep(7) ____ 7 3
sleep(4) ____ 4 3 3
sleep(20)____ 4 3 3 10
awake() _____ 3 3 3 10
awake() _____ 2 3 3 10
awake() _____ 1 3 3 10
awake() _____ 0 3 3 10
awake() _____ 2 3 10
awake() _____ 1 3 10
awake() _____ 0 3 10
awake() _____ 2 10

the Job class:

This Object maintain one data member of the remaining sleeping time and some methods to get and set this data
member.