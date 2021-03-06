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

package com.raywenderlich.android.loveletter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.loveletter.model.FragmentType
import com.raywenderlich.android.loveletter.model.Letter
import com.raywenderlich.android.loveletter.viewholder.LetterViewHolder
import kotlinx.android.synthetic.main.view_holder_letter.view.*

class LetterAdapter(private val fragmentType: FragmentType) :
  RecyclerView.Adapter<LetterViewHolder>() {

  private val list: MutableList<Letter> = mutableListOf()

  private var itemClickListener: ((Letter) -> Unit)? = null
  private var itemDeleteListener: ((Letter) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return LetterViewHolder(inflater, parent)
  }

  override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
    val item = list[position]
    holder.itemView.setOnClickListener {
      itemClickListener?.invoke(item)
    }
    holder.itemView.ivDelete.setOnClickListener {
      itemDeleteListener?.invoke(item)
    }
    holder.bind(item, fragmentType)
  }

  fun setItemClickListener(listener: (letter: Letter) -> Unit) {
    itemClickListener = listener
  }

  fun setItemDeleteListener(listener: (letter: Letter) -> Unit) {
    itemDeleteListener = listener
  }

  override fun getItemCount(): Int = list.size
  fun update(listItems: List<Letter>) {
    list.clear()
    list.addAll(listItems)
    notifyDataSetChanged()
  }
}


