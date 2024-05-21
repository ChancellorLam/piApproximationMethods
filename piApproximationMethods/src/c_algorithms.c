#include <jni.h>
#include <stdio.h>

JNIEXPORT jdouble JNICALL Java_piApproximationMethods_CodeInC_madhavaLeibniz
  (JNIEnv *, jobject, jlong numOfIterations)
{
    double currentValue = 0;
    long long denominator = 1;
    double newValue;
    int percentageCounter = 0;
    long long counter = 0;
    double tenPercentNumOfIterations = numOfIterations * 0.1;

    for (long long i = 0; i < numOfIterations; i++) { // 4/1 - 4/3 + 4/5 - 4/7 + 4/9...± 4/n
        if (i % 2 == 0) {	// + terms
            newValue = currentValue + 4.0 / denominator;
        }
        else { // - terms
            newValue = currentValue - 4.0 / denominator;
        }
        currentValue = newValue;
        denominator = denominator + 2;

        if (counter >= tenPercentNumOfIterations) {
            percentageCounter = percentageCounter + 10;
            printf("%d%% completed.\n", percentageCounter);
            fflush(stdout);
            counter = 0;
        }
        else {
            counter++;
        }
    }
    puts("100% completed.");
    fflush(stdout);

    return currentValue;
}
JNIEXPORT jdouble JNICALL Java_piApproximationMethods_CodeInC_madhavaLeibnizPrintCalc
  (JNIEnv *, jobject, jlong numOfIterations)
{
    double currentValue = 0;
	long long denominator = 1;
	double newValue;

    for (long long i = 0; i < numOfIterations; i++) { // 4/1 - 4/3 + 4/5 - 4/7 + 4/9...± 4/n
        if (i % 2 == 0) {	// + terms
            newValue = currentValue + 4.0 / denominator;
            printf("Iteration %lld: %.17f + 4/%lld = %.17f\n", i + 1, currentValue, denominator, newValue);
        }
        else { // - terms
            newValue = currentValue - 4.0 / denominator;
            printf("Iteration %lld: %.17f - 4/%lld = %.17f\n", i + 1, currentValue, denominator, newValue);
        }
        currentValue = newValue;
        denominator = denominator + 2;
    }
    fflush(stdout);

    return currentValue;
}