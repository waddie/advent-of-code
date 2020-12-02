(ns two.core-test
  (:require [clojure.test :refer :all]
            [two.core :refer :all]))

(def example-one-answer 2)
(def example-two-answer 1)

(def input (two.core/process-passwords ["1-3 a: abcde"
                                        "1-3 b: cdefg"
                                        "2-9 c: ccccccccc"]))

(deftest example-for-part-one
  (testing "Example for part one."
    (is (= example-one-answer
           (count (two.core/valid-passwords input two.core/wrong-valid-password?))))))

(deftest example-for-part-two
  (testing "Example for part two."
    (is (= example-two-answer
           (count (two.core/valid-passwords input two.core/valid-password?))))))
