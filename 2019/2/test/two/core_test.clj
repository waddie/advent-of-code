(ns two.core-test
  (:require [clojure.test :refer :all]
            [two.core :refer :all]))

(deftest example-for-part-one
  (testing "Example for part one."
    (is (= 3500 (two.core/process-opcodes [1 9 10 3 2 3 11 0 99 30 40 50])))))

(deftest example-for-part-two
  (testing "Check part two finds original inputs for part one."
    (let [input (mapv read-string (clojure.string/split
                                   (slurp "resources/input.txt")
                                   #","))
          {noun :noun
           verb :verb} (two.core/search-for-inputs input 4138687)]
      (is (= 1202 (+ (* 100 noun) verb))))))
