(ns freq-seq-enum)

(def fs (atom #{}))

(defn locally-frequents
  [sdb min-sup]
  (let [uniq-sdb (map (comp frequencies set) sdb)
        freqs (apply merge-with + uniq-sdb)]
    (->> freqs
         (filter #(<= min-sup (second %)))
         (map #(vector (str (first %)) (second %))))))


(defn project-sdb
  [sdb prefix]
  (if (empty? prefix) sdb
                      (into [] (->> sdb
                                    (filter #(re-find (re-pattern (str (last prefix))) %))
                                    (map #(subs % (inc (.indexOf % (str (last prefix))))))
                                    (remove empty?)))))


(defn freq-seq
  [sdb prefix prefix-support min-sup frequent-seqs]
  (if ((complement empty?) prefix) (swap! fs conj [prefix prefix-support]))
  (let [lf (locally-frequents sdb min-sup)]
    (if (empty? lf) nil
                    (for [[item sup] lf] (freq-seq (project-sdb sdb (str prefix item)) (str prefix item) sup min-sup @fs)))))

(defn mine-freq-seqs
  [sdb min-sup]
  (freq-seq sdb "" 0 min-sup @fs))

;;simple test for now. TDD coming!
(mine-freq-seqs ["CAABC" "ABCB" "CABC" "ABBCA"] 2)
(deref fs)
