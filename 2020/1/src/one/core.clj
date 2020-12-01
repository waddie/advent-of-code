(ns one.core
  (:gen-class)
  (:require [clojure.string :as string])
  (:require [clojure.pprint :as pprint]))

(def target 2020)

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
  (let [input   (mapv read-string (string/split
                                 (slurp "resources/input.txt")
                                 #"\n"))
        pair    (find-pair-equal-to-sum input target)
        triplet (find-triplet-equal-to-sum input target)]

    (println "Part one")
    (print "Pair: ")
    (pprint/pprint pair)
    (println "Answer:" (reduce * pair) "\n")

    (println "Part two")
    (print "Triplet: ")
    (pprint/pprint triplet)
    (println "Answer:" (reduce * triplet))))
