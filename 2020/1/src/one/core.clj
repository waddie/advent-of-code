(ns one.core
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure.pprint :as pprint]))

(def target-number 2020)
(def input-file "resources/input.txt")

(defn find-pair-equal-to-sum
  [v target]
  (let [input-set (into #{} v)]
    (filter #(contains? input-set
                        (- target %))
            v)))

(defn find-triplet-equal-to-sum
  [v target]
  (let [input-set (into #{} v)]
    (distinct
      (for [x v
            y v
            :when (contains? input-set
                             (- target x y))]
        (- target x y)))))

(defn -main
  [& args]
  (let [input   (mapv read-string (string/split (slurp input-file) #"\n"))
        pair    (find-pair-equal-to-sum input target-number)
        triplet (find-triplet-equal-to-sum input target-number)]
    (println "Part one")
    (print "Pair: ") (pprint/pprint pair)
    (println "Answer:" (reduce * pair) "\n")

    (println "Part two")
    (print "Triplet: ") (pprint/pprint triplet)
    (println "Answer:" (reduce * triplet))))
