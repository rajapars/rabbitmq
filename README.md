#Case 1
Write a sample code for message broker listener in case failure happened during listener. Pick kafka or
rabbitmq. Point is, we need to retry for 5 times maximum in case listener fails. Backoff period between
each retry is one minute fixed. Make it non-blocking (e.g. if A - B - C - D comes in sequence, and B failed,
C still processed without waiting B retry)