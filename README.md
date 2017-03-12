# Frequent Sequence Enumeration

A frequent sequences enumeration algorithm I implemented for fun (and lack of solutions in clojure) as described in :

Frequent Closed Sequence Mining without Candidate Maintenance, J. Wang, J. Han,
and C. Li, IEEE Trans. on Knowledge and Data Engineering 19(8):1042-1056, IEEE
Press, Piscataway, NJ, USA 2007

## Use it.

Import and call `mine-freq-seqs` with your sequence database (in string format) and the minimum support.

### Example
`(mine-freq-seqs ["CAABC" "ABCB" "CABC" "ABBCA"] 2)
 (deref fs)`

yields
`=>
 #{["B" 4]
   ["BC" 4]
   ["AB" 4]
   ["CA" 3]
   ["CAC" 2]
   ["AC" 4]
   ["ABC" 4]
   ["CAB" 2]
   ["A" 4]
   ["CABC" 2]
   ["ABB" 2]
   ["CC" 2]
   ["CB" 3]
   ["C" 4]
   ["BB" 2]
   ["CBC" 2]
   ["AA" 2]}`

## TODO

Loads. Among which
 - No manual deref to get result.
 - Efficient closed sequence support.