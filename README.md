# Heap

## Competitive Programming Questions Involving Heaps (mkmalik.com)

Here are some competitive programming questions that involve Min Heaps and Max Heaps. These problems will help you practice and sharpen your coding skills.

## Questions

1. **Kth Smallest Element in a Stream**
   - Given a stream of integers and an integer `k`, find the kth smallest element in the stream at any point in time. Use a Min Heap to efficiently maintain the top k elements.

2. **Kth Largest Element in an Array**
   - Given an array of integers and an integer `k`, find the kth largest element in the array. This can be solved using a Min Heap to keep track of the k largest elements.

3. **Merge K Sorted Lists**
   - Given `k` sorted linked lists, merge them into a single sorted linked list. Use a Min Heap to efficiently extract the smallest element from the heads of the lists.

4. **Top K Frequent Elements**
   - Given an array of integers, return the k most frequent elements. Use a Max Heap to keep track of the frequency of each element.

5. **Find Median from Data Stream**
   - Implement a data structure that supports adding numbers and finding the median efficiently. Use a Min Heap and Max Heap to keep track of the lower half and the upper half of the numbers.

6. **Sliding Window Maximum**
   - Given an array and a sliding window size `k`, return the maximum value for each sliding window. Use a Deque (or a Max Heap) to maintain the maximum in the current window.

7. **Reorganize String**
   - Given a string, rearrange the characters so that no two adjacent characters are the same. If it’s not possible, return an empty string. Use a Max Heap to keep track of the character frequencies.

8. **Heap Sort**
   - Implement the heap sort algorithm using a Max Heap. This will help you understand the sorting mechanism using heaps.

9. **Check for Almost Duplicate Elements**
   - Given an array of integers, check if there are two distinct indices `i` and `j` such that `abs(nums[i] - nums[j]) <= t` and `abs(i - j) <= k`. Use a Min Heap to maintain the sliding window of size `k`.

10. **Find the Nth Ugly Number**
    - An ugly number is a positive number whose prime factors only include `2`, `3`, and `5`. Use a Min Heap to generate the sequence of ugly numbers efficiently.

## Tips for Solving

- **Understand the Properties**: Review the properties of Min Heaps and Max Heaps and how they can be used in different scenarios.
- **Think about Edge Cases**: Consider edge cases like empty input, very large or very small numbers, and constraints specified in the problem.
- **Optimize with Heaps**: Heaps are often used to maintain a dynamic list of the smallest or largest elements, so think about how they can help reduce time complexity.

These questions should give you a good mix of theoretical understanding and practical application. Happy coding!
