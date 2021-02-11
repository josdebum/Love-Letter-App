/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.loveletter.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.loveletter.R
import com.raywenderlich.android.loveletter.adapter.LetterAdapter
import com.raywenderlich.android.loveletter.model.FragmentType
import com.raywenderlich.android.loveletter.model.Letter
import com.raywenderlich.android.loveletter.viewmodel.LettersViewModel
import kotlinx.android.synthetic.main.fragment_inbox.*


  class InboxFragment : Fragment(R.layout.fragment_inbox){

  private val lettersViewModel: LettersViewModel? = null

  private val adapter by lazy { LetterAdapter(FragmentType.INBOX) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    adapter.setItemClickListener {
      // TODO: navigate to presentation fragment
    }
    adapter.setItemDeleteListener {
      lettersViewModel?.deleteLetter(it, FragmentType.INBOX)
    }
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = adapter

    lettersViewModel?.inboxLettersLiveData?.observe(this, Observer { listItems: List<Letter> ->
      adapter.update(listItems)
    })
    lettersViewModel?.loadInboxLetters()
  }
}

