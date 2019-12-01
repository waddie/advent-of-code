(ns two.core
  (:gen-class))

(defn x-of-a-kind?
  "Predicate for exactly x of any letter in a string."
  [x s]
  (number? (some #{x} (vals (frequencies (seq s))))))

(defn checksum
  [input]
  (* (count (filter (partial x-of-a-kind? 2) input))
     (count (filter (partial x-of-a-kind? 3) input))))

(defn part-two
  [input]
  (println "Part two")
  input)

(defn -main
  [& args]
  (let [input (mapv read-string (clojure.string/split (slurp "resources/input.txt") #"\n"))]
    (println "Part one")
    (part-one input)

    (println "Part two")
    (part-two input)))
