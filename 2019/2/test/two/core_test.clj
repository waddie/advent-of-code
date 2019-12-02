(ns two.core-test
  (:require [clojure.test :refer :all]
            [two.core :refer :all]))

(deftest example-for-part-one
  (testing "Example for part one."
    (is (= 3500 (two.core/process-opcodes [1 9 10 3 2 3 11 0 99 30 40 50])))))
