import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem57_InsertInterval {
    class Interval {
        int start;
        int end;
        Interval(int s, int e) { start = s; end = e; }
    }

    class Solution {
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
            List<Interval> result = new ArrayList<>();
            Interval mergedInterval = new Interval(newInterval.start, newInterval.end);
            for (Interval currInterval : intervals) {
                if (currInterval.end < newInterval.start
                        || currInterval.start > newInterval.end) {
                    result.add(currInterval);
                } else {
                    if (currInterval.start < mergedInterval.start) {
                        mergedInterval.start = currInterval.start;
                    }
                    if (currInterval.end > mergedInterval.end) {
                        mergedInterval.end = currInterval.end;
                    }
                }
            }
            result.add(mergedInterval);
            result.sort(Comparator.<Interval> comparingInt((i -> i.start)));
            return result;
        }
    }
}
