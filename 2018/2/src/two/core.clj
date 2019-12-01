(ns two.core
  (:gen-class)
  (:require [clojure.data :as data]))

(defn x-of-a-kind?
  "Predicate for exactly x of any letter in a string."
  [x s]
  (number? (some #{x} (vals (frequencies (seq s))))))

(defn checksum
  "Total strings containing 2 of the same letter multiplied by total strings containing 3 of the same letter."
  [input]
  (* (count (filter (partial x-of-a-kind? 2) input))
     (count (filter (partial x-of-a-kind? 3) input))))

(defn common-letters
  "Return common letters of IDs containing only one difference."
  [input]
  (loop [current   (first input)
         remaining (rest input)]
    (let [candidate (first (filter (fn [candidate]                                     ; data/diff returns [things-only-in-a] [things-only-in-b] [common-elements]
                                     (= 1 (count (filter char? (first candidate)))))   ; for correct answer, [things-only-in-a] will contain one char and a bunch of nils
                                   (map (fn [candidate]                                ; diff current string with every string that follows it
                                          (data/diff (seq current) (seq candidate)))
                                        remaining)))]
      (if (seq (last candidate))                                                ; if we got a match, [common-elements] will be a seq
        (apply str (last candidate))                                            ; convert [char] (and one nil) to a string
        (recur (first remaining)
               (rest remaining))))))

(defn -main
  [& args]
  (let [input (sort (clojure.string/split (slurp "resources/input.txt")
                                          #"\n"))]
    (println "Part one")
    (println "Answer:" (checksum input))

    (println "Part two")
    (println "Answer:" (common-letters input))))
